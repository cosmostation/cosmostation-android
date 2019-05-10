//
//  GuideListTableViewCell.swift
//  Cosmostation
//
//  Created by yongjoo on 09/05/2019.
//  Copyright © 2019 wannabit. All rights reserved.
//

import UIKit

class GuideListTableViewCell: UITableViewCell {

    @IBOutlet weak var categoryLabel: UILabel!
    @IBOutlet weak var expendArrowImg: UIImageView!
    @IBOutlet weak var separatorLine: UIView!
    @IBOutlet weak var detailStackView: UIStackView!
    
    @IBOutlet weak var title0View: UIView!
    @IBOutlet weak var title0Label: UILabel!
    @IBOutlet weak var title1View: UIView!
    @IBOutlet weak var title1Label: UILabel!
    @IBOutlet weak var title2View: UIView!
    @IBOutlet weak var title2Label: UILabel!
    @IBOutlet weak var title3View: UIView!
    @IBOutlet weak var title3Label: UILabel!
    @IBOutlet weak var title4View: UIView!
    @IBOutlet weak var title4Label: UILabel!
    @IBOutlet weak var title5View: UIView!
    @IBOutlet weak var title5Label: UILabel!
    @IBOutlet weak var title6View: UIView!
    @IBOutlet weak var title6Label: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        self.selectionStyle = .none
    }
}
