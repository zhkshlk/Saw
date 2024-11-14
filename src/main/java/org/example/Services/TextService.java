package org.example.Services;

import org.example.Model.Text;
import org.example.Repositories.TextRepository;
import org.springframework.stereotype.Service;

@Service
public class TextService {

    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public String getText(long id) {
        Text text = textRepository.findById(id).orElse(null);
        return text != null ? text.getText() : null;
    }
}
