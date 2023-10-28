//
//  HButtoncomp.swift
//  HealthSavy
//
//  Created by Eyimofe Oladipo on 10/24/23.
//

import SwiftUI

struct HButtonComp: View {
    var image: String = ""
    var title: String = ""
    var body: some View {
        VStack(alignment: .leading){
            Image(image)
                .resizable()
                .scaledToFill()
                .frame(width: 50,height:50 )
                .clipShape(Circle())
            Text(title)
                .foregroundColor(.white)
                .frame(maxWidth: .infinity, maxHeight: .infinity, alignment: .leading)
                .multilineTextAlignment(.leading)
                .font(.title2)
                .bold()
                .padding(.vertical)
        }
        .padding()
        .background(Color("blueColor"))
        .cornerRadius(20)
        .shadow(radius: 5)
    }
}

struct HButtoncomp_Previews: PreviewProvider {
    static var previews: some View {
        HButtonComp(image: "studentImage", title: "Studeffffffffffffffffnt")
    }
}
