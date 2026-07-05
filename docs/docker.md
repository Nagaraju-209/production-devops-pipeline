# Docker Commands

## Build

```bash
docker build -f docker/Dockerfile -t production-devops-pipeline:v1.0.0 .
```

## Run

```bash
docker run -d --name springboot-container -p 8082:8082 production-devops-pipeline:v1.0.0
```

## Logs

```bash
docker logs springboot-container
```

## Stop

```bash
docker stop springboot-container
```

## Remove

```bash
docker rm springboot-container
```

## Images

```bash
docker images
```