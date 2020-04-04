package AFS;

import arc.Core;
import arc.util.Log;
import mindustry.entities.traits.Entity;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.concurrent.TimeUnit;


import static mindustry.Vars.fireGroup;
import static mindustry.Vars.groundEffectGroup;

public class Main extends Plugin {
    private JSONObject alldata;
    private JSONObject data; //token, channel_id, role_id
    private Thread mt = Thread.currentThread();
    private int timer;

    public Main() throws InterruptedException {
        try {
            String pureJson = Core.settings.getDataDirectory().child("mods/settings.json").readString();
            alldata = new JSONObject(new JSONTokener(pureJson));
            if (!alldata.has("afs")){
                Log.err("settings.json missing afs");
                //this.makeSettingsFile("settings.json");
                return;
            } else {
                data = alldata.getJSONObject("afs");
            }
        } catch (Exception e) {
            if (e.getMessage().contains("File not found: config\\mods\\settings.json")){
                Log.err("AFS: settings.json file is missing.");
                return;
            } else {
                Log.err("AFS: Error reading JSON.");
                e.printStackTrace();
                return;
            }
        }

        Thread A = new Thread() {
            public void run() {
                Log.info("AFS  started successfully!");
                while (mt.isAlive()) {
                    //
                    for(Entity fire : fireGroup){
                        Call.onRemoveFire(fire.getID());
                    }
                    //
                    for(Entity groundEffect : groundEffectGroup){
                        Call.onPuddleRemoved(groundEffect.getID());
                    }
                    //
                    try {
                        TimeUnit.SECONDS.sleep(timer);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Log.info("AFS Stopped");
            }
        };
        if (data.has("timer")) {
            timer = data.getInt("timer");
            Log.info("AFS set to every " + timer + " seconds.");
            A.setDaemon(false);
            A.start();
            Log.info("Attempting to start AFS in 1 min...");
        } else {
            Log.err("AFS missing key `timer`");
        }
    }
}