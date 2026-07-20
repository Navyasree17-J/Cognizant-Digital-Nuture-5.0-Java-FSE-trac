### Containerization using Docker
## 🐳 Module: Essential Docker Commands & Container Operations

Containerization relies on manipulating lightweight execution instances via the Docker Daemon CLI. Mastering these basic Docker commands allows developers to fetch container templates, manage running processes, inspect memory filesystems, and interact dynamically with containerized applications.

---

### 1. Image Lifecycle Commands

Before a container can exist, Docker needs an **Image**—a read-only blueprint containing the application code, dependencies, libraries, and runtime instructions.

#### 📥 `docker pull`
Downloads an image blueprint from a remote container registry (such as Docker Hub) into your local machine's disk storage without running it immediately.

```bash
# Syntax: docker pull <repository-name>:<tag>
docker pull postgres:15-alpine
```

🖼️ `docker images`
Lists all container images currently stored on your local host system, including their repository names, tags, unique Image IDs, creation timestamps, and virtual sizes.

```Bash
docker images
```
Output Format Example:
Plaintext
| REPOSITORY  | TAG     |    IMAGE ID   |    CREATED     |   SIZE |
| :--- | :--- | :--- | :--- | :--- |
| postgres   |  15-alpine  | c70f6e5e8e11  | 2 weeks ago  |  379MB |
| nginx      |  latest     | a610669171f1  | 3 weeks ago  |  142MB |

🗑️ `docker rmi`
Removes one or more images from your local host machine storage to free up disk space.

Note: Docker will block you from deleting an image if an active or stopped container is still using it.

```Bash
# Syntax: docker rmi <image-id-or-name>
docker rmi postgres:15-alpine

# Force remove an image (if associated with stopped containers)
docker rmi -f c70f6e5e8e11
```
