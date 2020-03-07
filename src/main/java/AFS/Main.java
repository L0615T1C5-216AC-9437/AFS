package AFS;

import arc.util.Log;
import mindustry.entities.traits.Entity;
import mindustry.gen.Call;
import mindustry.plugin.Plugin;

import java.util.concurrent.TimeUnit;

import static mindustry.Vars.fireGroup;
import static mindustry.Vars.groundEffectGroup;

public class Main extends Plugin {
    public Main() throws InterruptedException {
        Thread A = new Thread() {
            public void run() {
                Log.info("NF started successfully!");
                while (true) {
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
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        A.start();
    }
}