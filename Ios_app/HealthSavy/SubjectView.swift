//
//  SubjectView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/27/23.
//

import SwiftUI

struct SubjectView: View {
    @EnvironmentObject var homeviewmodel: HomeViewModel
    @StateObject var subjectviewmodel = SubjectViewModel()
    @Environment(\.dismiss) var dismiss
    var body: some View {
        ZStack {
            ScrollView(){
                VStack(alignment: .leading){
                    HStack {
                        Text("What topic?")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                    }
                    HStack {
                        Spacer()
                        Text(subjectviewmodel.topic)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    subjectviewmodel.isClicked = true
                                    subjectviewmodel.currentlySelected = "topic"
                                }
                            }
                        
                    }
                    
                    HStack {
                        Text("What are you studying for?")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                        
                    }
                    HStack {
                        Spacer()
                        Text(subjectviewmodel.studyingFor)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    subjectviewmodel.isClicked = true
                                    subjectviewmodel.currentlySelected = "studyingFor"
                                }
                            }
                        
                    }
                    HStack {
                        Text("How much do you know and understand?")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                            .shadow(radius: 10)
                    }
                    HStack {
                        Spacer()
                        Text(subjectviewmodel.knowledge)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    subjectviewmodel.isClicked = true
                                    subjectviewmodel.currentlySelected = "knowledge"
                                }
                            }
                        
                    }
                    HStack {
                        Text("Specify your question")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                            .shadow(radius: 10)
                    }
                    HStack {
                        Spacer()
                        Text(subjectviewmodel.question)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    subjectviewmodel.isClickedQuestion = true
                                    subjectviewmodel.currentlySelected = "question"
                                }
                            }
                        
                    }
                    Spacer()
                    
                }
                .padding(.horizontal)
            }
            
            AiButton
            
            if subjectviewmodel.isClicked{
                PopUp()
                    .environmentObject(subjectviewmodel)
            }
            
            if subjectviewmodel.isClickedQuestion{
                PopUpQuestions()
                    .environmentObject(subjectviewmodel)
            }
            
        }
        .navigationBarTitleDisplayMode(.inline)
        .sheet(isPresented: $subjectviewmodel.showSheet) {
            AIResultView(message: $subjectviewmodel.responseMessage)
        }
    }
    var AiButton: some View{
        VStack{
            Spacer()
            HStack {
                Spacer()
                Button {
                    subjectviewmodel.showSheet.toggle()
                    subjectviewmodel.apiCall(with: "I have a \(subjectviewmodel.knowledge)  understanding of \(subjectviewmodel.topic) and I have  \(subjectviewmodel.studyingFor). \(subjectviewmodel.question)?")
                } label: {
                    Circle()
                        .foregroundColor(Color("blueColor"))
                        .frame(width: 100, height: 100)
                        .shadow(radius: 10)
                        .overlay(Image("midok")
                            .resizable()
                            .scaledToFit()
                            .frame(width: 50, height: 50))
                }
                .padding()
                
            }
        }
    }
}

struct SubjectView_Previews: PreviewProvider {
    static var previews: some View {
        SubjectView()
    }
}



