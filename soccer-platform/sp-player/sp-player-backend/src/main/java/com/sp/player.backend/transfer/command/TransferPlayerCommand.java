package com.sp.player.backend.transfer.command;

import com.sp.player.backend.command.TransferCommand;
import lombok.AllArgsConstructor;
import lombok.Value;
import value.Transfer;

@Value
@AllArgsConstructor
public class TransferPlayerCommand extends TransferCommand {

    private Transfer transfer;

}
