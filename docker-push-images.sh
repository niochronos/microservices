#!/bin/bash

# Array of images
declare -a images=(
    "accounts"
    "loans"
    "cards"
    "config-server"
    "eureka-server"
    "gateway-server"
)

declare tag=":s12"

# Loop through each images and run the commands
for image in "${images[@]}"; do
    echo --------------------------------------------------
    echo "Docker image push docker.io/niochronos/$image$tag"
    docker image push docker.io/niochronos/"$image$tag" || { echo "Docker push failed $image"; exit 1; }
    echo "Successfully pushed $image"
done

echo "All images pushed successfully!"
