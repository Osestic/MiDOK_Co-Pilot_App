"""
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
       # Define a system message to set the behaviour of the assistant
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
    #html_response = f"<html><body>{response.choices[0].text}</body></html>"
   # return html_response
  """
from flask import Flask, request, jsonify
import openai
import tiktoken
import logging

app = Flask(__name__)


openai.api_key = open("Secret Key Ohu OpenAi.txt","r").read().rstrip("\n")

messages = [{"role": "system", "content": "You are the best and most helpful healthcare AI with decades of years of information and data. I am only interested in information related to healthcare. Do not provide any information that is unrelated to healthcare. If a user asks a question unrelated to healthcare, respond with: I am a healthcare AI; I do not have the information on what you are requesting.Do not generate any content that is offensive, inappropriate, or harmful."}]


 

#POST request route
@app.route('/chat', methods=['POST']) 

def urbanHealthCareAi():
    num_tokens = 0 
    data = request.json
    messages = []  
    
    if 'message' in data:

           user_message = data['message']
           
           num_tokens = num_tokens_from_string(user_message, 'gpt-3.5-turbo')
          
           messages.append({'role': 'user', 'content': user_message})
          
           
       # Define a system message to set the behaviour of the assistant
           system_message = {
              'role': 'system',
              'content': 'You are the best and most helpful healthcare AI with decades of years of information and data. I am only interested in information related to healthcare. Do not provide any information that is unrelated to healthcare. If a user asks a question unrelated to healthcare, do not answer them but respond with: I am a healthcare AI; I do not have the information on what you are requesting. Do not provide any other information afterwards.Do not generate any content that is offensive, inappropriate, or harmful.'
              }

           messages.append(system_message)
         
         #Call ChatGPT to generate a response
           response = openai.ChatCompletion.create(
                model = 'gpt-3.5-turbo',
                messages = messages,
                temperature = 0,
                #max_tokens=50
 
           )

           logging.basicConfig(format='%(message)s')
           log = logging.getLogger(__name__) 
           log.warning("\nThe number of tokens in your message is %d", num_tokens)
           
           return jsonify({'response': response.choices[0].message['content']}),200
   
def num_tokens_from_string(string: str, encoding_for_model: str) -> int:
    """Returns the number of tokens in a text string."""
    encoding = tiktoken.encoding_for_model(encoding_for_model)
    num_tokens = len(encoding.encode(string))
    return num_tokens
    
def print_to_stdout(*a):
    # Here a is the array holding the objects
    # passed as the argument of the function
    print(*a, file=sys.stdout)

if __name__ == '__main__':
    app.run(debug=True, host="0.0.0.0", port=5000)
