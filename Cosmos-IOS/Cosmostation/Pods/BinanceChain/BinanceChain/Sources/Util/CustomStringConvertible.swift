public extension CustomStringConvertible {

    var description: String {

        // Use reflection to describe the object and its properties
        let name = String(describing: type(of: self))
        let mirror = Mirror(reflecting: self)
        let properties: [String] = mirror.children.compactMap ({
            guard let name = $0.label else { return nil }
            if let value = $0.value as? Double { return String(format: "%@: %f", name, value) }
            return String(format: "%@: %@", name, String(describing: $0.value))
        })
        return String(format: "%@ [%@]", name, properties.joined(separator: ", "))

    }

}
