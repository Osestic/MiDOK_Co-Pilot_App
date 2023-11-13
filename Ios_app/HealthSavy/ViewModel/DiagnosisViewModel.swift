//
//  DiagnosisViewModel.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/28/23.
//

import SwiftUI

class DiagnosisViewModel: ObservableObject {
    
    @Published var isClicked: Bool = false
    @Published var isClickedQuestion: Bool = false
    
    @Published var showSheet: Bool = false
    
    
    @Published var topic: String = ""
    @Published var symptoms: String = ""
    @Published var severity: String = ""
    @Published var duration: String = ""
    
    @Published var currentlySelected: String = ""
    
    
    @Published var search: String = ""
    let symptomsList = ["Fever","Fatigue", "Shortness of Breath" , "Joint Pain", "Loss of Taste or Smell",  "Nausea or Vomiting", "Muscle Aches", "Fatigue", "Persistent Cough" , "Sore throat"]
    let severityList = ["Low","Medium", "High"]
    let durationList = ["1 to 3 days", "4 to 6 days", "several days"]
    
    
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
