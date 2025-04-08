#!/bin/bash

# Array of project directories
declare -a projects=(
    "accounts"
    "../loans"
    "../cards"
    "../config-server"
    "../eureka-server"
    "../gateway-server"
)

# Loop through each directory and run the commands
for project in "${projects[@]}"; do
    echo --------------------------------------------------
    echo "Building project in $project"
    cd "$project" || { echo "Failed to navigate to $project"; exit 1; }
    mvn clean install -DskipTests=true || { echo "Build failed in $project"; exit 1; }
    echo "Successfully built project in $project"
done

echo "All projects built successfully!"
