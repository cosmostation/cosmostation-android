//
//  DecimalViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class DecimalViewController: UIViewController {
    
    @IBOutlet weak var deciamlBtn0: UIButton!
    @IBOutlet weak var deciamlBtn1: UIButton!
    @IBOutlet weak var deciamlBtn2: UIButton!
    
    @IBOutlet weak var deciamlBtn3: UIButton!
    @IBOutlet weak var deciamlBtn4: UIButton!
    @IBOutlet weak var deciamlBtn5: UIButton!
    
    @IBOutlet weak var deciamlBtn6: UIButton!
    @IBOutlet weak var deciamlBtn7: UIButton!
    @IBOutlet weak var deciamlBtn8: UIButton!
    @IBOutlet weak var deciamlBtn9: UIButton!
    
    @IBOutlet weak var deciamlBtnBack: UIButton!
    
    var DeciamlBtns: [UIButton] = [UIButton]()
    var Number: [String] = ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"]

    override func viewDidLoad() {
        super.viewDidLoad()
        self.DeciamlBtns = [self.deciamlBtn0, self.deciamlBtn1, self.deciamlBtn2,
                            self.deciamlBtn3, self.deciamlBtn4, self.deciamlBtn5,
                            self.deciamlBtn6, self.deciamlBtn7, self.deciamlBtn8, self.deciamlBtn9]
        
        onRefreshKeyBoard()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.diableButtons),
                                               name: Notification.Name("lockBtns"),
                                               object: nil)
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.enbleButtons),
                                               name: Notification.Name("unlockBtns"),
                                               object: nil)
        
        NotificationCenter.default.addObserver(self,
                                               selector: #selector(self.onRefreshKeyBoard),
                                               name: Notification.Name("KeyboardShuffle"),
                                               object: nil)
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("lockBtns"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("unlockBtns"), object: nil)
        NotificationCenter.default.removeObserver(self, name: Notification.Name("KeyboardShuffle"), object: nil)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
    }
    
    @objc func onRefreshKeyBoard() {
        Number.shuffle()
        for i in 0 ..< DeciamlBtns.count {
            self.DeciamlBtns[i].setTitle(Number[i], for: .normal)
        }
    }
    
    @IBAction func onDecimalClick(_ sender: UIButton) {
        let value:[String: String] = ["Keyboard": (sender.titleLabel?.text)!]
        NotificationCenter.default.post(name: Notification.Name("KeyboardClick"), object: nil, userInfo: value)
    }
    
    @IBAction func onDeleteClick(_ sender: UIButton) {
        let value:[String: String] = ["Keyboard": "delete"]
        NotificationCenter.default.post(name: Notification.Name("KeyboardClick"), object: nil, userInfo: value)
    }
    
    @objc func diableButtons() {
        UIApplication.shared.beginIgnoringInteractionEvents()
    }
    
    @objc func enbleButtons() {
        UIApplication.shared.endIgnoringInteractionEvents()
    }

}

extension MutableCollection where Indices.Iterator.Element == Index {
    mutating func shuffle() {
        let c = count
        guard c > 1 else { return }
        
        for (firstUnshuffled , unshuffledCount) in zip(indices, stride(from: c, to: 1, by: -1)) {
            let d: Int = numericCast(arc4random_uniform(numericCast(unshuffledCount)))
            guard d != 0 else { continue }
            let i = index(firstUnshuffled, offsetBy: d)
            self.swapAt(firstUnshuffled, i)
        }
    }
}
