import net.mamoe.mirai.Bot;
import net.mamoe.mirai.BotFactory;
import net.mamoe.mirai.contact.Contact;
import net.mamoe.mirai.event.GlobalEventChannel;
import net.mamoe.mirai.event.events.GroupMessageEvent;
import net.mamoe.mirai.message.data.At;
import net.mamoe.mirai.message.data.Image;
import net.mamoe.mirai.message.data.PlainText;
import net.mamoe.mirai.message.data.QuoteReply;
import org.jetbrains.annotations.NotNull;

import java.net.MalformedURLException;
import java.net.URL;
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
                if (x instanceof PlainText plainText){
                    if (plainText.getContent().toLowerCase().contains("hello")){
                        event.getSubject().sendMessage(new QuoteReply(event.getMessage()).plus("Yet, Another, Hello, World"));
                    }
                }
            });
        });

        var scanner = new Scanner(System.in);

        while (true){
            if (scanner.next().equals("exit")){
                bot.close();

                return;
            }
        }
    }
}
