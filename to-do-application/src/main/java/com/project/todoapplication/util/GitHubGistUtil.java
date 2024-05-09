package com.project.todoapplication.util;

import lombok.RequiredArgsConstructor;
import okhttp3.*;

import java.io.IOException;

@RequiredArgsConstructor
public class GitHubGistUtil {
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final String GITHUB_API_URL = "https://api.github.com/gists";

    public static String generateMarkdownContent(String projectTitle, int completedTodos, int totalTodos,
                                                 String[] pendingTodos, String[] completedTodosList) {
        StringBuilder markdownContent = new StringBuilder();
        markdownContent.append("# ").append(projectTitle).append("\n\n");
        markdownContent.append("**Summary:** ").append(completedTodos).append(" / ").append(totalTodos).append(" completed.\n\n");

        markdownContent.append("## Pending Todos\n");
        for (String todo : pendingTodos) {
            markdownContent.append("- [ ] ").append(todo).append("\n");
        }

        markdownContent.append("\n## Completed Todos\n");
        for (String todo : completedTodosList) {
            markdownContent.append("- [x] ").append(todo).append("\n");
        }

        return markdownContent.toString();
    }

    public static String createGist(String markdownContent, String githubToken) {
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(markdownContent, JSON);

        Request request = new Request.Builder()
                .url(GITHUB_API_URL)
                .addHeader("Authorization", "token " + githubToken)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to create gist. Response code: " + response.code());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while creating gist: " + e.getMessage(), e);
        }
    }
}
