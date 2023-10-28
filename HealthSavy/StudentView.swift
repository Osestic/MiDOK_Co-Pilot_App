//
//  StudentView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/21/23.
//

import SwiftUI

struct StudentView: View {
    @EnvironmentObject var homeviewmodel: HomeViewModel
    let list = ["Educational Resource", "School Specific Health Aid", "Mental Health Resource"]
    let list2 = ["Subjects", "Professional Exam", "Adivising"]
    @State var isClicked: Bool = false
    var body: some View {
        ScrollView(.vertical) {
            VStack(alignment: .leading){
                
                Text("Personalizing health care for students in college and HighSchool")
                    .padding([.top,.horizontal])
                Image("studentImage")
                    .resizable()
                    .scaledToFill()
                    .frame(height: 100, alignment: .top)
                    .clipped()
                    .cornerRadius(20)
                    .padding(.horizontal)
                    .padding(.bottom)
                
                        ForEach(0...2, id: \.self) { data in
                            NavigationLink {
                                SubjectView()
                            } label: {
                                Text(list2[data])
                                    .padding()
                                    .foregroundColor(.white)
                                    .frame(maxWidth: .infinity, alignment: .leading)
                                    .background(Color("blueColor"))
                                   .cornerRadius(20)
                            }
                        }
                        .padding(.horizontal)
                
                Spacer()
            }
            .navigationTitle("Educational Resources")
        }
    }
}

struct StudentView_Previews: PreviewProvider {
    static var previews: some View {
        StudentView()
       // HomeView()
    }
}
