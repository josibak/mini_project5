package miniproject.service;

import java.time.Duration;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.image.CreateImageRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class AIPublicationService {

    private final OpenAiService openAiService;

    public AIPublicationService(@Value("${openai.api-key}") String apiKey) {
        this.openAiService = new OpenAiService(apiKey, Duration.ofSeconds(60));
    }

    // 표지 이미지 생성 (DALL·E 사용)
    public String generateCover(String title, String content) {
        System.out.println("AI 서비스 시작! 제목: " + title + ", 내용: " + content);
        String prompt = "Create a bright and visually compelling book cover for a novel titled \"" 
        + title 
        + "\". The cover should reflect the core themes and mood of the story, which is about: " 
        + content 
        + ". Use a clean, artistic style with high visual clarity, and incorporate symbolic or emotional elements that align with the story. The overall tone should be light, hopeful, or inspiring. The design should be suitable for a modern literary or fiction book.";


        CreateImageRequest request = CreateImageRequest.builder()
                .prompt(prompt)
                .n(1)
                .size("1024x1024")
                .build();

        return openAiService.createImage(request)
                .getData()
                .get(0)
                .getUrl(); // 생성된 이미지 URL
    }

    // 요약문 생성 (GPT 사용)
    public String generateSummary(String content) {
        System.out.println("AI 서비스 시작! 내용: " + content);
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

