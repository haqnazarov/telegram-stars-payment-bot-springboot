package uz.haqnazarov.startspaymentbot.bot;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final BotConstant botConstant;

    @Override
    public void run(String... args) throws Exception {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botConstant.getToken(), new MyAmazingBot(botConstant));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
