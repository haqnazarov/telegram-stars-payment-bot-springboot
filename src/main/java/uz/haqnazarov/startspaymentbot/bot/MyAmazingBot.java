package uz.haqnazarov.startspaymentbot.bot;

import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.AnswerPreCheckoutQuery;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.invoices.SendInvoice;
import org.telegram.telegrambots.meta.api.methods.payments.RefundStarPayment;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.payments.LabeledPrice;
import org.telegram.telegrambots.meta.api.objects.payments.SuccessfulPayment;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

/**
 * Telegram Stars Payment Bot
 * <p>
 * This bot demonstrates how to:
 * 1. Send invoices for Telegram Stars payments.
 * 2. Handle successful payments.
 * 3. Process refunds using the /refund command.
 * <p>
 * Author: Javohir Haqnazarov @haqnazarov
 */
@RequiredArgsConstructor
public class MyAmazingBot implements LongPollingSingleThreadUpdateConsumer {

    private final TelegramClient telegramClient;

    public MyAmazingBot(BotConstant botConstant) {
        this.telegramClient = new OkHttpTelegramClient(botConstant.getToken());
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage()) {
            handleMessage(update);
        } else if (update.hasPreCheckoutQuery()) {
            handlePreCheckoutQuery(update);
        }
    }

    /**
     * Handle incoming messages from users.
     */
    private void handleMessage(Update update) {
        String text = update.getMessage().getText();
        Long chatId = update.getMessage().getChatId();

        if (update.getMessage().hasSuccessfulPayment()) {
            handleSuccessfulPayment(update);
            return;
        }

        if (text == null) return;

        if (text.equalsIgnoreCase("/start")) {
            sendInvoice(chatId);
        } else if (text.startsWith("/refund")) {
            processRefund(chatId, text);
        }
    }

    /**
     * Send invoice to the user.
     */
    private void sendInvoice(Long chatId) {
        try {
            telegramClient.execute(SendInvoice.builder()
                    .chatId(chatId)
                    .title("Premium Plan")
                    .description("You are purchasing the Professional Premium Plan")
                    .payload("premium_12345") // Unique payment identifier
                    .currency("XTR")
                    .price(new LabeledPrice("Premium Plan", 10)) // 10 Stars
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send invoice", e);
        }
    }

    /**
     * Handle successful payment and send confirmation message.
     */
    private void handleSuccessfulPayment(Update update) {
        SuccessfulPayment payment = update.getMessage().getSuccessfulPayment();
        Long chatId = update.getMessage().getChatId();

        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text("<b>✅ Payment Successful! You are now subscribed to Premium.\n\n" +
                            "💳 Transaction ID: <code>" + payment.getTelegramPaymentChargeId() + "</code></b>\n\n" +
                            "♻️ If you want to request a refund, use the following command:\n" +
                            "<code>/refund " + payment.getTelegramPaymentChargeId() + "</code>")
                    .parseMode(ParseMode.HTML)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send payment confirmation", e);
        }
    }

    /**
     * Process refund request using Telegram Stars.
     */
    private void processRefund(Long chatId, String text) {
        String transactionId = text.replace("/refund", "").trim();

        if (transactionId.isEmpty()) {
            sendErrorMessage(chatId, "⚠️ Please provide a valid transaction ID.\n" +
                    "Example: /refund 1234567890");
            return;
        }

        try {
            telegramClient.execute(RefundStarPayment.builder()
                    .userId(chatId)
                    .telegramPaymentChargeId(transactionId)
                    .build());

            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text("✅ Refund request sent successfully!\nTransaction ID: <code>" + transactionId + "</code>")
                    .parseMode(ParseMode.HTML)
                    .build());

        } catch (TelegramApiException e) {
            sendErrorMessage(chatId, "❌ Refund failed. Please make sure the transaction ID is correct.");
        }
    }

    /**
     * Handle pre-checkout queries from Telegram.
     */
    private void handlePreCheckoutQuery(Update update) {
        try {
            telegramClient.execute(AnswerPreCheckoutQuery.builder()
                    .preCheckoutQueryId(update.getPreCheckoutQuery().getId())
                    .ok(true)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to answer pre-checkout query", e);
        }
    }

    /**
     * Send error messages to the user.
     */
    private void sendErrorMessage(Long chatId, String message) {
        try {
            telegramClient.execute(SendMessage.builder()
                    .chatId(chatId)
                    .text(message)
                    .parseMode(ParseMode.HTML)
                    .build());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Failed to send error message", e);
        }
    }
}
