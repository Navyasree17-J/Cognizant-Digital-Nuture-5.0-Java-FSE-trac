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
2. Container Execution & Process Management
Once an image exists locally, Docker spins up a Container—a runnable, isolated instance of that image.

🚀 `docker run`
Creates and starts a brand-new container instance from a target image. If the image does not exist locally, Docker will automatically execute a docker pull first before starting.

Crucial Flags for docker run:
* **`-d `(Detached mode):** Runs the container in the background, freeing up your terminal shell window.

* **`-p` (Port Mapping):** Binds a host machine port to a container internal port (-p <host_port>:<container_port>).

* **`--name:`** Assigns a custom human-readable name to the container instead of a random generated string.

* **`-e:`** Passes environment configuration variables inside the container space.

```Bash
# Spins up a detached Nginx container mapped to host port 8080
docker run -d -p 8080:80 --name my-web-server nginx:latest
```
📋 `docker ps`
Lists active containers running on your system.

```Bash
# Shows only currently active/running containers
docker ps

# Shows ALL containers (including stopped, exited, or failed instances)
docker ps -a
```
🛑 `docker stop`
Sends a graceful `SIGTERM` signal to a running container process, giving it a default 10-second timeout to complete active web requests, close database connections, and save states before shutting down.

```Bash
# Syntax: docker stop <container-id-or-name>
docker stop my-web-server
```
🧹 `docker rm`
Deletes a stopped container instance from memory and removes its writable container layer.

```Bash
# Removes a stopped container
docker rm my-web-server

# Force removes a container even if it is currently running (Sends SIGKILL)
docker rm -f my-web-server
```
3. Executing Commands Inside Running Containers
When diagnosing a container, you often need to attach terminal commands, inspect filesystems, or append parameters to running processes.

🐚 `docker exec`
Executes a new command inside a container that is already running. This is commonly used to open an interactive bash/sh terminal inside the container space.

Flags used with docker exec:
* **`-i` (Interactive):** Keeps STDIN open so you can type commands.

* **`-t` (TTY):** Allocates a pseudo-terminal shell interface.

```Bash
# Opens an interactive Bash terminal session inside a running container
docker exec -it my-web-server /bin/bash

# Executes a single quick command inside without opening a full interactive shell session
docker exec my-web-server cat /etc/nginx/nginx.conf
```
➕ Appending Commands During Container Startup (docker run `[IMAGE] [COMMAND]`)
When you issue a `docker run` statement, you can append a command at the end of the line. This overrides or appends to the default start command defined inside the container's original `Dockerfile.`

```Bash
# Starts a temporary Ubuntu container, overrides the default shell, and executes 'uname -a'
docker run --rm ubuntu:latest uname -a
```
(The --rm flag automatically cleans up and deletes the container instance immediately after execution ends).

🛠️ Real-World Command Walkthrough Cheat-Sheet
Here is a typical day-to-day lifecycle workflow executed in order:

```Bash
# Step 1: Pull an official Redis image from Docker Hub
docker pull redis:alpine

# Step 2: Verify the image downloaded to your local storage
docker images

# Step 3: Run Redis in detached background mode mapping port 6379
docker run -d -p 6379:6379 --name cache-db redis:alpine

# Step 4: Verify the container is running active
docker ps

# Step 5: Jump inside the running Redis container and execute Redis-CLI queries
docker exec -it cache-db redis-cli ping

# Step 6: Stop the Redis container gracefully
docker stop cache-db

# Step 7: View the stopped container status
docker ps -a

# Step 8: Remove the container instance and clean local image cache
docker rm cache-db
docker rmi redis:alpine
```
---

## 🚀 Module: Deep Dive into the `docker run` Command

The `docker run` command is the foundational operation in Docker container management. Under the hood, executing `docker run` triggers a multi-stage execution pipeline:
1. It checks if the specified image exists in the local daemon storage cache.
2. If absent, it automatically executes a `docker pull` to fetch the image from Docker Hub (or a configured private registry).
3. It creates a isolated, read-write container layer on top of the image structure.
4. It initializes network interfaces, assigns virtual IP parameters, maps host ports, and executes the default startup instruction.

---

### 1. Fundamental Command Anatomy

```bash
docker run [OPTIONS] IMAGE [COMMAND] [ARG...]
```
When you issue `docker run hello-world`, Docker executes the container in the foreground, streams the output directly to your standard terminal output (`STDOUT`), and exits immediately once the execution process completes.
2. Core Execution Flags & Use Cases
A. Running a Container Under a Specific Name (`--name`)
By default, Docker assigns randomly generated names (e.g., `trusty_hopper`, `focused_tesla`) to new containers. In production systems, explicit naming is required so other scripts, orchestrators, and developers can identify and target the container deterministically.
```Bash
# Assigns the explicit identifier 'production-cache-db'
docker run --name production-cache-db redis:alpine
```
B. Running a Container in the Background / Detached Mode (`-d`)
Long-running background services (e.g., web servers, databases, messaging brokers) should not hold your terminal shell session hostage. The -d flag runs the container in Detached Mode, running the process silently in the background and returning the unique 64-character Container ID to your terminal shell.

```Bash
# Starts an Nginx web server as a background service process
docker run -d --name enterprise-web-node nginx:latest
```
To inspect output from a detached container later:
```Bash
docker logs enterprise-web-node
```

C. Running a Container Interactively (`-it`)
When you need to step into a container environment for debugging, shell operations, or manual configuration, combine the Interactive (`-i`) and Pseudo-TTY (`-t`) flags.
* **`-i` (Interactive):** Redirects your host shell terminal input (STDIN) directly into the container.
* **`-t` (TTY):** Allocates an interactive terminal interface inside the container.
```Bash
# Boots a fresh Ubuntu instance and attaches an interactive Bash shell session
docker run -it --name ubuntu-debug-node ubuntu:latest /bin/bash
```
(Type exit inside the container terminal to terminate the process and return to your host OS).

D. Publishing Container Network Ports (`-p`)
By default, containers operate inside an isolated virtual network bridge and are completely inaccessible to external traffic or host machine requests. The `-p` flag maps a Host Machine Port directly to an internal Container Port (`-p <host_port>:<container_port>`).
```Bash
# Maps host machine port 8080 to the container's internal web server port 80
docker run -d -p 8080:80 --name global-api-gateway nginx:latest
```

🌐 Traffic Execution Flow:
`External Web Client Request $\rightarrow$ Host Machine (Port 8080) $\rightarrow$ Docker Network Interface Bridge $\rightarrow$ Internal Container (Port 80)`
E. Auto-Removing Containers Post-Execution (`--rm`)
Running short-lived tasks (such as database migrations, compilation tasks, or one-off diagnostic scripts) leaves behind stopped, dead container files on disk. The `--rm ` flag tells Docker to automatically delete and purge the container instance and its file layer from disk memory the moment its process completes or exits.
```Bash
# Executes a one-off database check and instantly wipes out the container on completion
docker run --rm postgres:15-alpine pg_isready -h localhost
```

💻 Enterprise Command Comparison Blueprint
| Operational Goal| Command Execution Pattern | Primary Use Case | 
| :--- | :--- | :--- |
|Foreground Run | docker run alpine ping google.com | Testing short terminal script executions.|
| Named Service | docker run --name my-redis redis | Assigning predictable names for scripts.| 
| Detached Daemon | docker run -d -p 80:80 nginx | Hosting production web apps & microservices. |
|Interactive Terminal | docker run -it alpine /bin/sh | Manual inspection and runtime troubleshooting. |
| Ephemeral Tasks | docker run --rm node:18-alpine node -v | Running build tools or test suits without residue. |

🛠️ Integrated Master Execution Scenario
Here is an enterprise setup combining these flags into a single operational command:

```Bash
docker run -d \
  --name payment-gateway-service \
  -p 9090:8080 \
  -e SPRING_PROFILES_ACTIVE=production \
  --restart unless-stopped \
  my-company/payment-service:v2.1.0
```

🔍 Deconstructing the Flags:
* **`-d:`** Runs as a decoupled background service.

* **`--name payment-gateway-service:`** Grants a clean reference label for logging and operations.

* **`-p 9090:8080:`** Routes incoming host traffic on port 9090 into the Java container application listening on port 8080.

* **`-e SPRING_PROFILES_ACTIVE=production:`** Injects an environment configuration parameter into the container runtime space.

* **`--restart unless-stopped:`** Ensures the Docker daemon automatically revives the container if it crashes or if the host machine reboots.

* **`my-company/payment-service:v2.1.0:`** The specific version-tagged image blueprint executed by Docker.
---

