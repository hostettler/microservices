import { Component, Input, OnInit } from '@angular/core';

import { NbMenuService, NbSidebarService } from '@nebular/theme';
import { AnalyticsService } from '../../../@core/utils';
import { LayoutService } from '../../../@core/utils';
import { KeycloakService } from '../../../services/keycloak/keycloak.service';


@Component({
    selector: 'fi-header',
    styleUrls: ['./header.component.scss'],
    templateUrl: './header.component.html',
})
export class HeaderComponent implements OnInit {

    @Input() position = 'normal';

    user: any;

    userMenu = [{ title: 'Profile' }, { title: 'Log out' }];

    constructor(private sidebarService: NbSidebarService,
        private menuService: NbMenuService,
        private analyticsService: AnalyticsService,
        private layoutService: LayoutService,
        private keycloakService: KeycloakService) {
    }

    ngOnInit() {
        this.user = {name: this.keycloakService.getFullName() };
        this.menuService.onItemClick()
            .subscribe((event) => {
                this.onContecxtItemSelection(event.item.title);
            });
    }

    onContecxtItemSelection(title) {
        if (title === 'Log out') {
            this.keycloakService.logout();
        } else {
            console.info(this.keycloakService.getFullName() + ' (' + this.keycloakService.getUsername() + ') ');
        }
    }

    toggleSidebar(): boolean {
        this.sidebarService.toggle(true, 'menu-sidebar');
        this.layoutService.changeLayoutSize();

        return false;
    }

    toggleSettings(): boolean {
        this.sidebarService.toggle(false, 'settings-sidebar');

        return false;
    }

    goToHome() {
        this.menuService.navigateHome();
    }

    startSearch() {
        this.analyticsService.trackEvent('startSearch');
    }

    logout() {
        this.keycloakService.logout();
    }
}
