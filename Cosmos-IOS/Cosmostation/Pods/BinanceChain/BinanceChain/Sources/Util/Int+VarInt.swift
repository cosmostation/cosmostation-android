import Foundation

extension Int {

    var varint: Data {
        var data = Data()
        var v = UInt64(self)
        while (v > 127) {
            data.append(UInt8(v & 0x7f | 0x80))
            v >>= 7
        }
        data.append(UInt8(v))
        return data
    }

}
