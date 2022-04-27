package app.explore.listener;

import app.explore.controller.BlockController;
import app.explore.controller.ExploreController;
import view.MainStage;

public class BlockListener {
    BlockController blockController = new BlockController();

    public void eventOccur(String s , int userId , int visitorId){
        if (s.equals("unblock")){
            blockController.unblock(userId , visitorId);

        }
        if (s.equals("block")){
            blockController.block(userId, visitorId);
        }
        MainStage.getInstance().user = blockController.getUser(userId);
        MainStage.getInstance().visitor = blockController.getUser(visitorId);
        MainStage.getInstance().back();
        return;
    }
}
