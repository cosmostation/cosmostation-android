//
//  AlphabetViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 26/03/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class AlphabetViewController: UIViewController {
    
    @IBOutlet weak var alphaBtn0: UIButton!
    @IBOutlet weak var alphaBtn1: UIButton!
    @IBOutlet weak var alphaBtn2: UIButton!
    @IBOutlet weak var alphaBtn3: UIButton!
    @IBOutlet weak var alphaBtn4: UIButton!
    @IBOutlet weak var alphaBtn5: UIButton!
    @IBOutlet weak var alphaBtn6: UIButton!
    
    @IBOutlet weak var alphaBtn7: UIButton!
    @IBOutlet weak var alphaBtn8: UIButton!
    @IBOutlet weak var alphaBtn9: UIButton!
    @IBOutlet weak var alphaBtn10: UIButton!
    @IBOutlet weak var alphaBtn11: UIButton!
    @IBOutlet weak var alphaBtn12: UIButton!
    @IBOutlet weak var alphaBtn13: UIButton!
    
    @IBOutlet weak var alphaBtn14: UIButton!
    @IBOutlet weak var alphaBtn15: UIButton!
    @IBOutlet weak var alphaBtn16: UIButton!
    @IBOutlet weak var alphaBtn17: UIButton!
    @IBOutlet weak var alphaBtn18: UIButton!
    @IBOutlet weak var alphaBtn19: UIButton!
    @IBOutlet weak var alphaBtn20: UIButton!
    
    @IBOutlet weak var alphaBtn21: UIButton!
    @IBOutlet weak var alphaBtn22: UIButton!
    @IBOutlet weak var alphaBtn23: UIButton!
    @IBOutlet weak var alphaBtn24: UIButton!
    @IBOutlet weak var alphaBtn25: UIButton!
    
    @IBOutlet weak var alphaBtnBack: UIButton!
    
    var AlphaBtns: [UIButton] = [UIButton]()
    var Char: [String] = ["A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
                          "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
                          "W", "X", "Y", "Z"]

    override func viewDidLoad() {
        super.viewDidLoad()
        self.AlphaBtns = [self.alphaBtn0, self.alphaBtn1, self.alphaBtn2, self.alphaBtn3, self.alphaBtn4, self.alphaBtn5, self.alphaBtn6, self.alphaBtn7, self.alphaBtn8, self.alphaBtn9,
                          self.alphaBtn10, self.alphaBtn11, self.alphaBtn12, self.alphaBtn13, self.alphaBtn14, self.alphaBtn15, self.alphaBtn16, self.alphaBtn17, self.alphaBtn18, self.alphaBtn19,
                          self.alphaBtn20, self.alphaBtn21, self.alphaBtn22, self.alphaBtn23, self.alphaBtn24, self.alphaBtn25]
        
        onRefreshKeyBoard()
        for i in 0 ..< Char.count {
            self.AlphaBtns[i].isEnabled = false;
        }

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
//        print("AlphabetViewController  viewDidAppear")
        for i in 0 ..< Char.count {
            self.AlphaBtns[i].isEnabled = true;
        }
    }
    
    @objc func onRefreshKeyBoard() {
        Char.shuffle()
        for i in 0 ..< Char.count {
            self.AlphaBtns[i].setTitle(Char[i], for: .normal)
        }
    }
    
    @IBAction func onAlphabetClick(_ sender: UIButton) {
        let value:[String: String] = ["Keyboard": (sender.titleLabel?.text)!]
        NotificationCenter.default.post(name: Notification.Name("KeyboardClick"), object: nil, userInfo: value)
    }
    
    @IBAction func onBackClick(_ sender: UIButton) {
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
