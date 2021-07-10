import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.Scanner;

public class MiraiBot {
    public static void main(@NotNull String[] args) throws MalformedURLException {
        var botQq = Long.parseLong(args[0]);
        var group = 110838222L;

        var bot = BotFactory.INSTANCE.newBot(botQq, args[1]);

        bot.login();

        var operator = bot.getGroup(group);

        var listener = GlobalEventChannel.INSTANCE.subscribeAlways(GroupMessageEvent.class, event -> {
            event.getMessage().forEach(x -> {
                if (x instanceof PlainText plainText) {

                }
            });
        });

        var scanner = new Scanner(System.in);

        while (true) {
            if (scanner.next().equals("exit")) {
                bot.close();

                return;
            }
        }
    }
}
