//
//  GuideDetailViewController.swift
//  Cosmostation
//
//  Created by yongjoo on 10/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class GuideDetailViewController: BaseViewController {
    
    var guideData:GuideCategory?
    var guideDetail:Int?

    @IBOutlet weak var guideTitleLabel: UILabel!
    @IBOutlet weak var guideDetailTextView: UITextView!
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.guideTitleLabel.text = guideData?.titles[guideDetail!]
        self.guideDetailTextView.text = guideData?.details[guideDetail!]
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationController?.setNavigationBarHidden(false, animated: animated)
        self.navigationController?.navigationBar.topItem?.title = NSLocalizedString("title_guide", comment: "")
        self.navigationItem.title = NSLocalizedString("title_guide", comment: "")
        self.navigationController?.navigationBar.setBackgroundImage(UIImage(), for: UIBarMetrics.default)
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}
