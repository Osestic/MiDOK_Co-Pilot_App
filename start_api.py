import subprocess

# Define the command to start your ChatGPT API
command = ["/opt/render/project/src/.venv/bin/python", "AI_ChatGPT_AIAdvantange.py"]

# Run the command
subprocess.run(command, check=True)
