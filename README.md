# 🌌 Telegram Stars Payment Bot

---

## 🚀 Overview

Welcome to the **Telegram Stars Payment Bot**, a slick and modern payment solution powered by **Java Spring Boot**. This
bot enables users to make seamless payments using **Telegram Stars** directly in Telegram, with support for premium
subscriptions and refunds. Its clean, modular architecture ensures scalability and ease of use.

---

## 🌟 Features

- 💸 **Seamless Payments**: Accept Telegram Stars for quick and secure transactions.
- 🔍 **Payment Validation**: Verify transactions using Telegram’s `PreCheckoutQuery`.
- 🏅 **Premium Subscriptions**: Automatically grant Premium status after successful payments.
- 🔄 **Easy Refunds**: Process refunds with a simple `/refund <transaction_id>` command.
- 🌐 **Real-Time API**: Integrates smoothly with the Telegram Bot API.
- 🛠 **Clean Codebase**: Built with a maintainable Spring Boot architecture.

---

## 🛠 Tech Stack

- **Java 17** – Modern, robust, and feature-rich.
- **Spring Boot 3+** – Lightweight and production-ready framework.
- **Telegram Bot API (Stars Payment)** – For seamless payment integration.
- **Maven** – Streamlined dependency and build management.
- **Lombok** – Cleaner code with less boilerplate.

---

## ⚙️ Setup Guide

Get your bot up and running in minutes! 🚀

1. **Clone the Repo**
   ```bash
   git clone https://github.com/haqnazarov/telegram-stars-payment-bot-springboot.git
   cd telegram-stars-payment-bot-springboot
   ```

2. **Set Bot Token**  
   Open `src/main/resources/application.properties` and add your Telegram bot token:
   ```properties
   spring.application.name=StarsPaymentBot
   bot.token=YOUR_TELEGRAM_BOT_TOKEN
   ```

3. **Launch the Bot**  
   Run one of these commands:
   ```bash
   ./mvnw spring-boot:run
   ```  
   *or*
   ```bash
   mvn spring-boot:run
   ```

---

## 💳 How Payments Work

1. User clicks **Pay with Stars** in Telegram.
2. Bot generates a payment invoice using Telegram Stars.
3. Telegram sends a `PreCheckoutQuery`, and the bot validates it.
4. Upon successful payment, the user is granted a **Premium subscription**.

---

## ↩️ Refunding Payments

To issue a refund, use this command in Telegram:

```
/refund <transaction_id>
```  

The bot processes the refund and notifies the user instantly.

---

## 📸 UI Sneak Peek

*Stay tuned!* Screenshots or a GIF of the payment flow will be added soon to showcase the bot’s smooth interface! 🎬

---

## 📜 License

Released under the **MIT License**. Feel free to use, tweak, and share! 🌍

---

## 🚀 Start Now!

Ready to revolutionize payments with Telegram Stars? Clone the repo, configure your bot, and dive in! Questions? Reach
out and let’s make payments out-of-this-world! 🌟

> Crafted with 💻 by [HAQNAZAROV](https://t.me/haqnazarov)
