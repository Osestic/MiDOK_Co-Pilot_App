//
//  ContentView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/21/23.
//

import SwiftUI

struct chatResponse: Codable{
    let response: String
}
struct ContentView: View {
    @StateObject var vm: ContentViewModel = ContentViewModel()
    @State var textField: String = ""
    @State var isClicked: Bool = false
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                TextField("Enter your name", text: $textField)
                    .padding()
                    .background(.regularMaterial)
                .cornerRadius(20)
                Button {
                    vm.apiCall(with: textField)
                    isClicked.toggle()
                } label: {
                    Text("Ask")
                        .foregroundColor(.white)
                        .padding()
                        .background(.red)
                        .cornerRadius(20)
                }
                
            }
            HStack(spacing: 10) {
                Text("Response from Api:")
                if isClicked && vm.responseMessage.isEmpty{
                    ProgressView()
                }else{
                    Text(vm.responseMessage)
                }
            }
            
            if(!vm.responseMessage.isEmpty){
                Button {
                    vm.responseMessage = ""
                    isClicked.toggle()
                } label: {
                    Text("Clear")
                        .foregroundColor(.white)
                        .padding()
                        .background(.red)
                        .cornerRadius(20)
                }
            }
        }
        .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

class ContentViewModel: ObservableObject {
    
    @Published var responseMessage: String = ""
    
    func apiCall(with text: String = ""){
        guard let url = URL(string: "https://ai-hackathon-ap.onrender.com/chat") else {
            return
        }
        print("lol")
        
        var request = URLRequest(url: url)
        // method post
        request.httpMethod = "POST"
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        let body: [String: AnyHashable] = [
            "message" : text
        ]
        
        request.httpBody = try?  JSONSerialization.data(withJSONObject: body, options: .fragmentsAllowed)
        
        // make the request
        let task = URLSession.shared.dataTask(with: request) { data , _, error in
            guard let data, error == nil else{
                return
            }
            do{
                let response = try JSONDecoder().decode(chatResponse.self, from: data)
                DispatchQueue.main.async {
                    self.responseMessage = response.response
                }
                print("Success: \(response.response)")
            }
            catch let error {
                print(error)
            }
        }
        task.resume()

    }
}


