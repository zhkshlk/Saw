package org.example.Services;

import org.example.Game;
import org.example.Model.Text;
import org.example.Repositories.A11Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class A11Service implements Game {

    private static final long TEXT_ID = 2; // ID записи, из которой мы будем брать текст
    private static final int CORRECT_ANSWER = 36;

    @Autowired
    private A11Repository textRepository;

    // Метод для получения содержимого текста из базы данных
    public String getFileContent() {
        Text text = textRepository.findById(TEXT_ID);
        if (text != null) {
            return text.getText().replace("\n", "<br/>"); // Конвертируем новые строки в <br/>
        } else {
            return "Ошибка при загрузке текста из базы данных.";
        }
    }

    // Метод для проверки ответа
    public String checkAnswer(int number, int attempts) {
        String message;
        if (number == CORRECT_ANSWER) {
            message = "Вы нашли правильный ответ!";
        } else {
            attempts--;
            if (attempts > 0) {
                message = "Неправильный ответ. Осталось попыток: " + attempts;
            } else {
                message = "Вы проиграли. Попробуйте снова.";
            }
        }
        return message;
    }

    // Метод для уменьшения количества попыток
    public int decreaseAttempts(int attempts) {
        return attempts - 1;
    }
}
