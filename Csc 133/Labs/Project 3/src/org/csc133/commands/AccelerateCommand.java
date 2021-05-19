package org.csc133.commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import org.csc133.a3.GameWorld;

public class AccelerateCommand extends Command {

    private final GameWorld gw;

    public AccelerateCommand(GameWorld gw){
        super("Accelerate");
        this.gw = gw;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        gw.accelerate();
    }
}