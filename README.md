# Java AI Integration Platform

A Spring Boot REST API that connects a Java application to a large language model through Spring AI. The application exposes a small HTTP interface and keeps provider configuration outside the source code.

## What it does

- Accepts chat prompts through a REST endpoint.
- Uses Spring AI's `ChatClient` to send prompts to an OpenAI-compatible chat-completions API.
- Returns the model response as JSON.
- Validates incoming requests and exposes an Actuator health endpoint.

The default endpoint configuration is suitable for Groq's OpenAI-compatible API. Other compatible providers can be configured with environment variables.

## Requirements

- JDK 17 or newer
- Maven 3.9 or newer
- An API key for your selected model provider

## Configuration

Set these environment variables before starting the application:

| Variable | Required | Default | Description |
|---|---|---|---|
| `AI_API_KEY` | Yes | — | API key for the model provider |
| `AI_BASE_URL` | No | `https://api.groq.com/openai` | OpenAI-compatible API base URL |
| `AI_MODEL` | No | `llama-3.3-70b-versatile` | Model name supported by your provider |
| `SERVER_PORT` | No | `8080` | HTTP server port |

Never commit API keys. Supply them through your shell, deployment environment, or a secrets manager.

### PowerShell

```powershell
$env:AI_API_KEY = "your-api-key"
$env:AI_BASE_URL = "https://api.groq.com/openai"
$env:AI_MODEL = "llama-3.3-70b-versatile"
mvn spring-boot:run
```

### macOS / Linux

```bash
export AI_API_KEY="your-api-key"
export AI_BASE_URL="https://api.groq.com/openai"
export AI_MODEL="llama-3.3-70b-versatile"
mvn spring-boot:run
```

The service listens on `http://localhost:8080` unless `SERVER_PORT` is changed.

## API

### Send a chat prompt

`POST /api/v1/ai/chat`

Request:

```json
{
  "message": "Explain dependency injection in Spring Boot."
}
```

Example response:

```json
{
  "answer": "Dependency injection is a design technique ...",
  "modelProvider": "OpenAI-compatible endpoint"
}
```

Example using curl:

```bash
curl -X POST http://localhost:8080/api/v1/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"message":"Explain dependency injection in Spring Boot."}'
```

### Health check

`GET /actuator/health`

## Request flow

```text
HTTP client
    |
    v
AiChatController
    |
    v
AiChatService
    |
    v
Spring AI ChatClient
    |
    v
Configured model provider
    |
    v
JSON response
```

## Build and test

```bash
mvn clean verify
```

## Project layout

```text
src/
  main/
    java/dev/udayshetty/aiplatform/
      api/          REST controller and request/response models
      service/      AI interaction logic
      JavaAiIntegrationApplication.java
    resources/
      application.yml
  test/
    java/           Request validation tests
pom.xml
```

## Notes

This repository currently implements text chat only. It does not include document upload, RAG, vector storage, authentication, or conversation persistence. The API key and a model supported by the configured provider are required for successful AI responses.

## License

MIT
