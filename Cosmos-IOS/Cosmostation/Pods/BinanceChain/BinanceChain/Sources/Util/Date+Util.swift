import Foundation

extension Date {

    init(millisecondsSince1970: Double) {
        self.init(timeIntervalSince1970: millisecondsSince1970 / 1000)
    }
    
}
