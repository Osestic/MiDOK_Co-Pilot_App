//
//  PopUpProf.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/28/23.
//

import SwiftUI

struct PopUpProf: View {
    @EnvironmentObject var diagnosisviewmodel: DiagnosisViewModel
    var body: some View {
        ZStack{
            Color.black
                .opacity(0.4)
                .ignoresSafeArea()
                .onTapGesture{
                    diagnosisviewmodel.isClicked.toggle()
                }
            VStack{
                NavigationStack{
                    List{
                        if diagnosisviewmodel.currentlySelected == "symptoms"{
                            ForEach(searchsymptoms, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        diagnosisviewmodel.isClicked.toggle()
                                        diagnosisviewmodel.symptoms = data
                                        
                                    }
                            }
                            
                        }else if diagnosisviewmodel.currentlySelected == "severity"{
                            ForEach(searchseverity, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        diagnosisviewmodel.isClicked.toggle()
                                        diagnosisviewmodel.severity = data
                                        
                                    }
                            }
                        }else if diagnosisviewmodel.currentlySelected == "duration"{
                            ForEach(searchduration, id: \.self) {data in
                                Text(data)
                                    .onTapGesture{
                                        diagnosisviewmodel.isClicked.toggle()
                                        diagnosisviewmodel.duration = data
                                        
                                    }
                            }
                           
                        }
                        
                    }
                    
                }.searchable(text: $diagnosisviewmodel.search)
                    .frame(width: 350, height: 550)
                    .cornerRadius(20)
                Spacer()
            }
            
        }.zIndex(1.0)
        
    }
    
    var searchsymptoms: [String] {
        if diagnosisviewmodel.search.isEmpty {
            return diagnosisviewmodel.symptomsList
        } else {
            return diagnosisviewmodel.symptomsList.filter { $0.contains(diagnosisviewmodel.search) }
        }
    }
    var searchseverity: [String] {
        if diagnosisviewmodel.search.isEmpty {
            return diagnosisviewmodel.severityList
        } else {
            return diagnosisviewmodel.severityList.filter { $0.contains(diagnosisviewmodel.search) }
        }
    }
    var searchduration: [String] {
        if diagnosisviewmodel.search.isEmpty {
            return diagnosisviewmodel.durationList
        } else {
            return diagnosisviewmodel.durationList.filter { $0.contains(diagnosisviewmodel.search) }
        }
    }
}
struct PopUpProf_Previews: PreviewProvider {
    static var previews: some View {
        PopUpProf()
    }
}
