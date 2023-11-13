//
//  PopUpQuestions.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/28/23.
//

import SwiftUI

struct PopUpQuestions: View {
    @EnvironmentObject var subjectviewmodel: SubjectViewModel
    @State var plac: String = ""
    var body: some View {
        ZStack{
            Color.black
                .opacity(0.4)
                .ignoresSafeArea()
                .onTapGesture{
                    subjectviewmodel.isClickedQuestion.toggle()
                }
            VStack {
                TextField("Enter your question", text: $subjectviewmodel.question )
                    .padding()
                    .frame(width: 350, height: 550,alignment: .topLeading)
                    .background()
                    .overlay(
                        Button {
                            subjectviewmodel.isClickedQuestion.toggle()
                        } label: {
                            Text("Done")
                                .foregroundColor(.white)
                                .padding()
                                .background(Color("blueColor"))
                                .cornerRadius(20)
                                .padding()
                        }
                        
                        ,alignment: .bottomTrailing)
                    .cornerRadius(20)
                Spacer()
            }
            
        }
        
    }
}

struct PopUpQuestions_Previews: PreviewProvider {
    static var previews: some View {
        PopUpQuestions()
            .environmentObject(SubjectViewModel())
    }
}
