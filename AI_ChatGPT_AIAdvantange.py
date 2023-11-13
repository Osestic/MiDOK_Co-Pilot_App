
from flask import Flask, request, jsonify
import openai

app = Flask(__name__)



openai.api_key = open("Secret Key Ohu OpenAi.txt","r").read().rstrip("\n")

messages = [{"role": "system", "content": "You are the best and most helpful healthcare AI with decades of years of information and data. I am only interested in information related to healthcare. Do not provide any information that is unrelated to healthcare. If a user asks a question unrelated to healthcare, respond with: I am a healthcare AI; I do not have the information on what you are requesting.Do not generate any content that is offensive, inappropriate, or harmful."}]

@app.route('/chat', methods=['POST'])

def urbanHealthCareAi():

    data = request.json
    if isinstance(data, list) and len(data) > 0:
        messages = [{'role': 'user', 'content': data[0].get('message', '')}]  # Use 'user' role for the user message
    
        if messages[0]['content']:
       # Define a system message to set the behavior of the assistant
           system_message = {'role': 'system', 'content': 'You are the best and most helpful healthcare AI with decades of years of information and data. I am only interested in information related to healthcare. Do not provide any information that is unrelated to healthcare. If a user asks a question unrelated to healthcare, do not answer them but respond with: I am a healthcare AI; I do not have the information on what you are requesting. Do not provide any other information afterwards.Do not generate any content that is offensive, inappropriate, or harmful.'}
           messages.append(system_message)
         
         #Call ChatGPT to generate a response
           response = openai.ChatCompletion.create(
                model = 'gpt-3.5-turbo',
                messages = messages,
                #max_tokens=50
 
           )

           return jsonify({'response': response.choices[0].message['content']})

    return jsonify({'response':'No message was provided'})
  #  html_response = f"<html><body>{response.choices[0].text}</body></html>"
   # return html_response
  
if __name__ == '__main__':
    app.run(debug=True)
