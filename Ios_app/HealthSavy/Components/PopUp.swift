//
//  PopUp.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/27/23.
//

import SwiftUI

struct PopUp: View {
    @EnvironmentObject var subjectviewmodel: SubjectViewModel
    var body: some View {
        ZStack{
            Color.black
                .opacity(0.4)
                .ignoresSafeArea()
                .onTapGesture{
                    subjectviewmodel.isClicked.toggle()
                }
            VStack{
                NavigationStack{
                    List{
                        if subjectviewmodel.currentlySelected == "topic"{
                            ForEach(searchTopic, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        subjectviewmodel.isClicked.toggle()
                                            subjectviewmodel.topic = data
                                        
                                    }
                            }
                            
                        }else if subjectviewmodel.currentlySelected == "studyingFor"{
                            ForEach(searchStudyingFor, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        subjectviewmodel.isClicked.toggle()
                                        subjectviewmodel.studyingFor = data
                                        
                                    }
                            }
                        }else if subjectviewmodel.currentlySelected == "knowledge"{
                            ForEach(searchKnowledge, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        subjectviewmodel.isClicked.toggle()
                                        subjectviewmodel.knowledge = data
                                        
                                    }
                            }
                           
                        }else if subjectviewmodel.currentlySelected == "question"{
                        }
                        
                    }
                    
                }.searchable(text: $subjectviewmodel.search)
                    .frame(width: 350, height: 550)
                    .cornerRadius(20)
                Spacer()
            }
            
        }.zIndex(1.0)
        
    }
    
    var searchTopic: [String] {
        if subjectviewmodel.search.isEmpty {
            return subjectviewmodel.topicList
        } else {
            return subjectviewmodel.topicList.filter { $0.contains(subjectviewmodel.search) }
        }
    }
    var searchStudyingFor: [String] {
        if subjectviewmodel.search.isEmpty {
            return subjectviewmodel.studyingForList
        } else {
            return subjectviewmodel.studyingForList.filter { $0.contains(subjectviewmodel.search) }
        }
    }
    var searchKnowledge: [String] {
        if subjectviewmodel.search.isEmpty {
            return subjectviewmodel.knowledgeList
        } else {
            return subjectviewmodel.knowledgeList.filter { $0.contains(subjectviewmodel.search) }
        }
    }
}


struct PopUp_Previews: PreviewProvider {
    static var previews: some View {
                PopUp()
                    .environmentObject(SubjectViewModel())
        //HomeView()
    }
}
