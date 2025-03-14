@echo off
setlocal enabledelayedexpansion

:: Define project directories
set projects= accounts ..\loans ..\cards ..\config-server

:: Loop through each directory and run the commands
for %%p in (%projects%) do (
    echo Building project in %%p
    cd %%p || exit /b 1
    mvn clean install -DskipTests=true || (
        echo Build failed in %%p
        exit /b 1
    )
    echo Successfully built project in %%p
)

echo All projects built successfully!
endlocal
