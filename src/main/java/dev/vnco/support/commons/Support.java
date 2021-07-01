package dev.vnco.support.commons;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class Support {

    @Getter @Setter private String partnerName;
    @Getter @Setter private String skullPlayer;
    @Getter @Setter private String displayName;
    @Getter @Setter private int slot;
    @Getter @Setter private List<String> lore;
    @Getter @Setter private List<String> rewardsCommand;

    public Support(String partnerName, String skullPlayer, String displayName, int slot, List<String> lore, List<String> rewardsCommand){
        this.partnerName = partnerName;
        this.skullPlayer = skullPlayer;
        this.displayName = displayName;
        this.slot = slot;
        this.lore = lore;
        this.rewardsCommand = rewardsCommand;
    }

}
