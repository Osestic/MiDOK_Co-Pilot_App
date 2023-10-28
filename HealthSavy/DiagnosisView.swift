//
//  DiagnosisView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/28/23.
//

import SwiftUI

struct DiagnosisView: View {
    @EnvironmentObject var homeviewmodel: HomeViewModel
    @StateObject var diagnosisviewmodel = DiagnosisViewModel()
    @Environment(\.dismiss) var dismiss
    var body: some View {
        ZStack {
            ScrollView(){
                VStack(alignment: .leading){
                    HStack {
                        Text("What are your symptoms?")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                    }
                    HStack {
                        Spacer()
                        Text(diagnosisviewmodel.symptoms)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    diagnosisviewmodel.isClicked = true
                                    diagnosisviewmodel.currentlySelected = "symptoms"
                                }
                            }
                        
                    }
                    
                    HStack {
                        Text("Rate the Severity")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                        
                    }
                    HStack {
                        Spacer()
                        Text(diagnosisviewmodel.severity)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    diagnosisviewmodel.isClicked = true
                                    diagnosisviewmodel.currentlySelected = "severity"
                                }
                            }
                        
                    }
                    HStack {
                        Text("Duration of feeling")
                            .foregroundColor(.white)
                            .padding(.horizontal)
                            .padding(.vertical,10)
                            .background(Color("blueColor"))
                            .cornerRadius(20)
                            .shadow(radius: 10)
                    }
                    HStack {
                        Spacer()
                        Text(diagnosisviewmodel.duration)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .onTapGesture {
                                withAnimation() {
                                    diagnosisviewmodel.isClicked = true
                                    diagnosisviewmodel.currentlySelected = "duration"
                                }
                            }
                        
                    }
//                    HStack {
//                        Text("Specify your question")
//                            .foregroundColor(.white)
//                            .padding(.horizontal)
//                            .padding(.vertical,10)
//                            .background(Color("blueColor"))
//                            .cornerRadius(20)
//                            .shadow(radius: 10)
//                    }
//                    HStack {
//                        Spacer()
//                        Text(diagnosisviewmodel.question)
//                            .padding()
//                            .background(.regularMaterial)
//                            .cornerRadius(20)
//                            .onTapGesture {
//                                withAnimation() {
//                                    diagnosisviewmodel.isClickedQuestion = true
//                                    diagnosisviewmodel.currentlySelected = "question"
//                                }
//                            }
//
//                    }
                    Spacer()
                    
                }
                .padding(.horizontal)
            }
            
            AiButton
            
            if diagnosisviewmodel.isClicked{
                PopUpProf()
                    .environmentObject(diagnosisviewmodel)
            }
            
        }
        .navigationBarTitleDisplayMode(.inline)
        .sheet(isPresented: $diagnosisviewmodel.showSheet) {
            AIResultView(message: $diagnosisviewmodel.responseMessage)
        }
    }
    var AiButton: some View{
        VStack{
            Spacer()
            HStack {
                Spacer()
                Button {
                    diagnosisviewmodel.showSheet.toggle()
                    diagnosisviewmodel.apiCall(with: "The patient is experiencing \(diagnosisviewmodel.symptoms) with a severity of \(diagnosisviewmodel.severity) for the past \(diagnosisviewmodel.duration). Can you provide closest related diseases? and put the possible treatment after each one")
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

struct DiagnosisView_Previews: PreviewProvider {
    static var previews: some View {
        DiagnosisView()
    }
}
