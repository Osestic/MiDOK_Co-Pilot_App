//
//  HomeView.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/21/23.
//

import SwiftUI


struct HomeView: View {
    init() {
      // Large Navigation Title
        UINavigationBar.appearance().largeTitleTextAttributes = [.foregroundColor: UIColor(named: "blueColor") ?? UIColor(.blue)]
      // Inline Navigation Title
      UINavigationBar.appearance().titleTextAttributes = [.foregroundColor: UIColor(named: "blueColor") ?? UIColor(.blue)]
    }
    @StateObject var homeviewmodel = HomeViewModel()
    let list = ["Student", "Health Professional", "Non-Health Professional"]
    private let  adaptiveColumn = [
        GridItem(.adaptive(minimum: 170),spacing: 20)
    ]
    var body: some View {
        NavigationView {
            ScrollView {
            VStack {
                HStack(){
                    Text("Who are you?")
                        .foregroundColor(.secondary)
                        .font(.title2)
                        .padding(.horizontal)
                    Spacer()
                }
             
                    LazyVGrid(columns: adaptiveColumn, spacing: 20) {
                        ForEach(0...2, id: \.self) { data in
                            NavigationLink {
                                if (data == 0) {
                                    StudentView()
                                }else if data == 1{
                                    HealthProfessionalView()
                                }else{
                                    NonHealthProfessionalView()
                                }

                            } label: {
                                HButtonComp(image: "studentImage", title: list[data])
                            }
                        }
                    }
                    .padding()
                }
            
            }
            
            .navigationTitle("MiDOK")
            .accentColor(.white)
            
            
            
        }

        .environmentObject(homeviewmodel)
        
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        HomeView()
    }
}
