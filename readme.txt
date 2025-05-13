## Login to your DockerHub

docker login -u username

# To pull the Docker image

docker pull charishmagajula/java-factorial-server:latest

# To run the container

docker run -d -p docker 8080:8080 charishmagajula/java-factorial-server

# To access the application 

http://localhost:8080/factorial?number=<your_number>⁠
