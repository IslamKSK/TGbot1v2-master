package org.example.bot;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TGbot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "ktits24_bot";
    }

    public String getBotToken() {
        return "6841290220:AAFeNDpcXHeC_4LSghW6dMIt7AFCvGlU5I4";
    }

    private static final String API_KEY = "daadb69047063506f29e9bd211b7d881 ";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();


            switch (messageText) {
                case "/start":
                    try {
                        startCommandReceived(chatId, update.getMessage().getChat().getFirstName());
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/help":
                    try {
                        helpCommand(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/rasp205":
                    try {
                        rasp205KSK(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/calls":
                    try {
                        calls(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/weather":
                    try {
                        weather(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/translate":
                    try {
                        Translate(chatId, update);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                case "/wiki":
                    try {
                        Wiki(chatId);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    try {
                        sendMessage(chatId, "Неизвестная команда!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
            }

        }

    }
    private void sendReplyMessage(Long chatId) {
        try {
            execute(SendMessage.builder().chatId(String.valueOf(chatId)).text("Напишите слово на английском").build());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void Translate(long chatId, Update update) throws TelegramApiException {
        Map<String, String> dictionary = new HashMap<>();
        dictionary.put("hello", "привет");
        dictionary.put("world", "мир");
        dictionary.put("java", "джава");

        System.out.print("Введите слово для перевода: ");
        String word = "hello";

                String translation = dictionary.get(word);

                if (translation != null) {
                    sendMessage(chatId, "Перевод слова \"" + word + "\": " + translation);
                } else {
                    sendMessage(chatId,"Перевод не найден");
                }
            }

    private void Wiki(long chatId) throws TelegramApiException {
        Map<String, String> dictionaryWiki = new HashMap<>();

        dictionaryWiki.put("Стол", "Стол — предмет мебели, имеющий приподнятую горизонтальную или наклонную поверхность и предназначенный для размещения предметов, выполнения работ, принятия пищи, игр, рисования, обучения и другой деятельности.");
        dictionaryWiki.put("Джава", "Java представляет собой язык программирования и платформу вычислений, которая была впервые выпущена компанией Sun Microsystems в 1995 г.");
        dictionaryWiki.put("Видеокарта", "Видеока́рта — устройство, преобразующее графический образ, хранящийся как содержимое памяти компьютера (или самого адаптера), в форму, пригодную для дальнейшего вывода на экран монитора.");

        System.out.print("Введите слово для перевода: ");
        String wordWiki = "Джава";

        String translationWiki = dictionaryWiki.get(wordWiki);

        if (translationWiki != null) {
            sendMessage(chatId, " \"" + wordWiki + "\": " + translationWiki);
        } else {
            sendMessage(chatId,"Слово не найдено");
        }
    }
    private void helpCommand(long chatId) throws TelegramApiException {
        String helpClist = "/start - запуск бота\n" +
                "/help - список команд\n" +
                "/rasp205 - расписание занятий группы 205КСК\n" +
                "/calls - расписание звонков\n" +
                "/weather - погода в Казани\n" +
                "/translate - Англо-русский переводчик\n" +
                "/wiki - википедия";

        sendMessage(chatId, helpClist);
    }

    private void calls(long chatId) throws TelegramApiException {
        {
            String callsText = "Понедельник-Пятница\n" +
                    "------------------------------\n" +
                    "1 пара: 8:00-9:30    ~ Перемена 10 минут\n" +
                    "2 пара: 9:40-11:10   ~ Перемена 40 минут\n" +
                    "3 пара: 11:50-13:20  ~ Перемена 20 минут\n" +
                    "4 пара: 13:40-15:10  ~ Перемена 10 минут\n" +
                    "5 пара: 15:20-16:50  ~ Перемена 10 минут\n" +
                    "6 пара: 17:00-18:30  ~ Перемена 10 минут\n" +
                    "7 пара: 18:40-20:10\n" +
                    "\n" +
                    "Суббота\n" +
                    "-----------------------------\n" +
                    "1 пара: 8:00-9:30    ~ Перемена 10 минут\n" +
                    "2 пара: 9:40-11:10   ~ Перемена 20 минут\n" +
                    "3 пара: 11:30-13:00  ~ Перемена 10 минут\n" +
                    "4 пара: 13:10-14:40  ~ Перемена 10 минут\n" +
                    "5 пара: 14:50-16:20  ~ Перемена 10 минут\n" +
                    "6 пара: 16:30-18:00  ~ Перемена 10 минут";
            sendMessage(chatId, callsText);
        }
    }

    private void startCommandReceived(long chatId, String name) throws TelegramApiException {
        String answer = "Привет, " + name + ", ты запустил телеграм-бот Помощник КТИТС. Используй команду /help для просмотра функций бота";
        sendMessage(chatId, answer);
    }

    private void sendMessage(long chatId, String textToSend) throws TelegramApiException {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(textToSend);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private void rasp205KSK(long chatId) throws TelegramApiException {
        String rasp = "Понедельник с 11:50 до 16:50\n" +
                "3. Проектирование (1307)\n" +
                "4. ОАИП (305)\n" +
                "5. Вышмат (301)\n" +
                "\n" +
                "Вторник с 8:00 до 13:20\n" +
                "1-3. Ремонт и обслуживание (1316)\n" +
                "\n" +
                "Среда с 9:40 до 13:20\n" +
                "2. Проектирование (1307)\n" +
                "3. Электротехника (401)\n" +
                "\n" +
                "Четверг с 9:40 до 16:50\n" +
                "2. Дискретка (308)\n" +
                "3. Ин.яз (407)\n" +
                "4. Фин. грамотность (416)\n" +
                "5. Вышмат (301)\n" +
                "\n" +
                "Пятница с 11:50 до 16:50\n" +
                "3. ОАИП (1305)\n" +
                "4. Электротехника (413)\n" +
                "5. Физ-ра\n" +
                "\n" +
                "Суббота с 13:10 до 16:20\n" +
                "4. Метрология (410)\n" +
                "5. Дискретная математика (308)";
        sendMessage(chatId, rasp);
    }

    private void weather(long chatId) throws TelegramApiException {

        String city = "Kazan";
        String endpoint = BASE_URL + city + "&appid=" + API_KEY;

        double temperature;
        int humidity = 0;
        double temperature1 = 0;
        try {
            URL url = new URL(endpoint);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            temperature = json.getJSONObject("main").getInt("temp");
            humidity = json.getJSONObject("main").getInt("humidity");
            temperature1 = temperature - 270;

        } catch (IOException e) {
            e.printStackTrace();
        }

        String tempKazan = "Температура в городе " + city + ": " + temperature1 + "°"
                + "\nВлажность: " + humidity + "%";
        sendMessage(chatId, tempKazan);
    }
}
