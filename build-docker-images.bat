@echo off
setlocal enabledelayedexpansion

:: Define project directories
set projects= accounts ..\loans ..\cards ..\config-server ../eureka-server ../gateway-server

:: Loop through each directory and run the commands
for %%p in (%projects%) do (
    echo --------------------------------------------------
    echo Building project in %%p
    cd %%p || exit /b 1
    mvn compile jib:dockerBuild || (
        echo Build failed in %%p
        exit /b 1
    )
    echo Successfully built project in %%p
)

echo All projects built successfully!
endlocal
