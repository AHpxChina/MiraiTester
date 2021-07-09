import Genshin.GenshinQuery;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.PlainText;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.util.Scanner;
import java.util.stream.Collectors;

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
                    if (plainText.getContent().toLowerCase().contains("genshin")) {
                        var split = plainText.getContent().split("\\s+");

                        try{
                            Long.parseLong(split[1]);

                            var index = GenshinQuery.getGenshinIndex(split[1], args[2]);

                            if (index != null) {
                                var avatars = index.avatars.stream().map(z -> z.name).collect(Collectors.joining(","));

                                event.getSubject().sendMessage(new At(event.getSender().getId()).plus("\n" + avatars));
                            }
                        }catch (Exception e){
                            e.printStackTrace();
                        }



                    }
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
