//
//  SubjectView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/27/23.
//

import Foundation


class SubjectViewModel: ObservableObject {

    @Published var isClicked: Bool = false
    @Published var isClickedQuestion: Bool = false
    
    @Published var showSheet: Bool = false
    
    
    @Published var topic: String = ""
    @Published var studyingFor: String = ""
    @Published var knowledge: String = ""
    @Published var question: String = ""
    
    @Published var currentlySelected: String = ""
    
    
    @Published var search: String = ""
    let topicList = ["Anatomy and Physiology","Biochemistry", "Biotechnology" , "Ecology", "Evolutionary Biology",  "Genetics", "Cell Biology", "Marine Biology"]
    let studyingForList = ["Self study","Final Exam", "Test", "Quiz"]
    let knowledgeList = ["Excellent", "Great", "Good", "Bad", "Horrible"]
    
    
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
