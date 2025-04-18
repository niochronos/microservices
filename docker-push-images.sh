#!/bin/bash

# Array of images
declare -a images=(
    "accounts"
    "loans"
    "cards"
    "config-server"
    "eureka-server"
    "gateway-server"
    "message"
)

declare tag=":s17wm"

# Loop through each images and run the commands
for image in "${images[@]}"; do
    echo " "
    echo "[ Docker image push docker.io/niochronos/$image$tag ]"
    docker image push docker.io/niochronos/"$image$tag" || { echo "Docker push failed $image$tag"; exit 1; }
    echo "[ Successfully pushed $image$tag ]"
done

echo " "
echo "[ All images with tag '$tag' pushed successfully! ]"
