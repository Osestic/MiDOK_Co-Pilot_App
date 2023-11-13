//
//  AIResultView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/28/23.
//

import SwiftUI

struct AIResultView: View {
    @EnvironmentObject var subjectviewmodel: SubjectViewModel
    
    
    @Binding var message: String
    var body: some View {
        ZStack{
            VStack {
                Image("midok")
                    .resizable()
                    .scaledToFit()
                    .frame(width: 50, height: 50)
                    .padding()
                
                ScrollView{
                    if message == "" {
                        ProgressView()
                    }else{
                        Text(message)
                            .padding()
                            .background(.regularMaterial)
                            .cornerRadius(20)
                            .padding()
                    }
                }

                
                Spacer()
            }
        }
    }
}

struct AIResultView_Previews: PreviewProvider {
    static var previews: some View {
        AIResultView(message: .constant("lol"))
           // .environmentObject(SubjectViewModel())
    }
}
