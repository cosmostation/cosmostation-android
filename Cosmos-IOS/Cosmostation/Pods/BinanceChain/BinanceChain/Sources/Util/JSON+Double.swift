import Foundation
import SwiftyJSON

extension JSON {

    // Handle doubles returned as strings, eg. "199.97207842"
    var doubleString: Double? {
        guard (self.exists()) else { return nil }
        return self.doubleValue
    }

}

