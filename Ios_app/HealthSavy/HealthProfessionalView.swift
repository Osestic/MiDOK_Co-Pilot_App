//
//  HealthProfessionalView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/21/23.
//

import SwiftUI

struct HealthProfessionalView: View {
    @EnvironmentObject var homeviewmodel: HomeViewModel
    let list = ["disease", "healthknow", "studentImage"]
    let list2 = ["Disease Diagnosis & Treatment", "Health Knowledge Base", "Real Word Data Intake"]
    let list3 = ["Identify", "Learn", "Take"]
    @State var isClicked: Bool = false
    var body: some View {
        ScrollView(.vertical) {
            VStack(alignment: .leading){
                
                Text("Personalizing health care for students in college and HighSchool")
                    .padding([.bottom,.horizontal])
                    .font(.caption2)
                ForEach(0...2, id: \.self) { data in
                    NavigationLink {
                        DiagnosisView()
                    } label: {
                        HStack {
                            Image(list[data])
                                .resizable()
                                .scaledToFill()
                                .frame(width: 50, height: 50)
                                .cornerRadius(10)
                                .padding([.leading,.vertical],10)
                            VStack(alignment: .leading) {
                                Text(list2[data])
                                    .foregroundColor(.white)
                                    .bold()
                                Text(list3[data])
                                    .foregroundColor(.secondary)
                                    .font(.caption)
                                    .multilineTextAlignment(.leading)
                            }
                            
                        }
                        .frame(maxWidth: .infinity, alignment: .leading)
                        .background(Color("blueColor"))
                        .cornerRadius(15)
                        .shadow(radius: 5)
                    }
                }
                .padding(.horizontal)
                
                Spacer()
            }
            .navigationTitle("Health Professional")
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    Image("midok")
                        .resizable()
                        .scaledToFit()
                        .frame(width: 30, height: 30)
//                        .padding()
                }
            }
        }
    }
    
}

struct HealthProfessionalView_Previews: PreviewProvider {
    static var previews: some View {
        HealthProfessionalView()
    }
}
