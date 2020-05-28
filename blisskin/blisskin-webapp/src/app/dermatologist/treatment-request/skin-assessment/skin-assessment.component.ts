import {Component, Input, OnInit} from '@angular/core';
import {SkinProperties} from '../../../model/skinProperties';

@Component({
    selector: 'app-skin-assessment',
    templateUrl: './skin-assessment.component.html',
    styleUrls: ['./skin-assessment.component.scss']
})
export class SkinAssessmentComponent implements OnInit {
    @Input() selectedSkinIssues;
    @Input() skinProperties: SkinProperties;

    allSkinIssues = [
        {name: 'Acne'},
        {name: 'Blackheads'},
        {name: 'Whiteheads'},
        {name: 'Inflammation'},
        {name: 'Eczema'},
        {name: 'Psoriasis'},
        {name: 'Hives'},
        {name: 'Sun damage'},
        {name: 'Rosacea'},
        {name: 'Dryness'},
        {name: 'Flakiness'},
        {name: 'Wrinkles'},
        {name: 'Fine lines'},
        {name: 'Redness'},
        {name: 'Skin texture'},
        {name: 'Enlarged pores'},
        {name: 'Clogged pores'},
        {name: 'Hyperpigmentation'},
        {name: 'Dark spots'},
        {name: 'Dullness'},
        {name: 'Scarring'},
        {name: 'Age spots'}];

    constructor() {
    }

    ngOnInit(): void {
    }
}
