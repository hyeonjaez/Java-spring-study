package starcraft;


import starcraft.player.Player;

public class Judge {
    public boolean isGameQuit(Player player) {
        return player.getTribe().getUnitList().isEmpty();
    }

    public void printWin(Player player, Player player2) {
        if (isGameQuit(player)) {
            System.out.println(player2.getName() + "이겼습니다.");
        } else {
            System.out.println(player.getName() + "이겼습니다.");
        }
    }
}
