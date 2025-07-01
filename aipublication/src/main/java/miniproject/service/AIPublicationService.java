package miniproject.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class AIPublicationService {

    private final OpenAiService openAiService;

    public AIPublicationService(@Value("${openai.api-key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey);
    }

    // 표지 이미지 생성 (DALL·E 사용)
    public String generateCover(String title, String content) {
        String prompt = "Generate a book cover for a book titled '" + title + "' about: " + content;

        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(prompt)
                .n(1)
                .size("512x512")
                .build();

        return openAiService.createImage(request)
                .getData()
                .get(0)
                .getUrl(); // 생성된 이미지 URL
    }

    // 요약문 생성 (GPT 사용)
    public String generateSummary(String content) {
        CompletionRequest request = CompletionRequest.builder()
                .prompt("다음 내용을 3문장으로 요약해줘:\n" + content)
                .model("gpt-3.5-turbo-instruct")
                .maxTokens(150)
                .temperature(0.7)
                .build();

        return openAiService.createCompletion(request)
                .getChoices()
                .get(0)
                .getText()
                .trim();
    }
}

